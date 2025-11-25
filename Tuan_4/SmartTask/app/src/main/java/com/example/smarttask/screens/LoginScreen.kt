package com.example.smarttask.screens // Đảm bảo package đúng

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttask.R
import com.example.smarttask.navigation.AppRoutes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
// import kotlinx.coroutines.tasks.await // <-- DÒNG NÀY ĐÃ ĐƯỢC XÓA HOẶC COMMENT LẠI
import kotlinx.coroutines.tasks.await // <-- Nếu bạn dùng thư viện tasks, thì import này là đúng

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val firebaseAuth = FirebaseAuth.getInstance()

    val googleSignInClient = remember {
        getGoogleSignInClient(context)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val idToken = account.idToken!!
                coroutineScope.launch {
                    isLoading = true
                    try { // Thêm try-catch ở đây để bắt lỗi await
                        val credential = GoogleAuthProvider.getCredential(idToken, null)
                        // Lệnh .await() này vẫn giữ nguyên, nó sẽ tự dùng phiên bản mới
                        firebaseAuth.signInWithCredential(credential).await()
                        Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoutes.TASK_LIST) {
                            popUpTo(AppRoutes.LOGIN) { inclusive = true }
                        }
                    } catch (e: Exception) { // Bắt lỗi chung khi await
                        Log.w("LoginScreen", "Firebase sign in failed", e)
                        Toast.makeText(context, "Xác thực Firebase thất bại.", Toast.LENGTH_LONG).show()
                    } finally {
                        isLoading = false // Luôn tắt loading dù thành công hay lỗi
                    }
                }
            } catch (e: ApiException) {
                isLoading = false
                Log.w("LoginScreen", "Google sign in failed", e)
                Toast.makeText(context, "Đăng nhập Google thất bại.", Toast.LENGTH_LONG).show()
            }
        } else {
            isLoading = false
            Toast.makeText(context, "Đã hủy đăng nhập.", Toast.LENGTH_SHORT).show()
        }
    }

    // Giao diện (giữ nguyên)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )
        Text("Welcome", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    isLoading = true
                    launcher.launch(googleSignInClient.signInIntent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign in with Google")
            }
        }
        TextButton(onClick = { navController.navigate(AppRoutes.FORGOT_PASSWORD) }) {
            Text("Quên mật khẩu?")
        }
        Text("© UTH SmartTasks", fontSize = 12.sp)
    }
}

// Hàm helper (giữ nguyên)
private fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, gso)
}