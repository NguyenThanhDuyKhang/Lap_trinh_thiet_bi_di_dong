package com.example.findmycar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    // Vị trí đỗ xe: Landmark 81
    private val carLocation = LatLng(10.795719, 106.720986)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

        setContent {
            val context = LocalContext.current
            var userLocation by remember { mutableStateOf<LatLng?>(null) }

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {

                    GoogleMapView(userLocation)

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                Toast.makeText(context, "Vị trí xe mặc định là Landmark 81", Toast.LENGTH_SHORT).show()
                            }
                        ) { Text("Save Location") }

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                val uri = android.net.Uri.parse(
                                    "geo:${carLocation.latitude},${carLocation.longitude}?q=${carLocation.latitude},${carLocation.longitude}(Landmark+81)"
                                )
                                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, uri)
                                intent.setPackage("com.google.android.apps.maps")
                                context.startActivity(intent)
                            }
                        ) { Text("Find My Car") }


                        if (userLocation != null) {
                            val distance = floatArrayOf(0f)
                            android.location.Location.distanceBetween(
                                userLocation!!.latitude, userLocation!!.longitude,
                                carLocation.latitude, carLocation.longitude,
                                distance
                            )
                            Text("Khoảng cách tới xe: ${"%.2f".format(distance[0] / 1000)} km")
                        }
                    }

                    Button(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (isGranted()) {
                                getCurrentLocation(fusedLocationClient) {
                                    userLocation = it
                                }
                            } else {
                                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }
                        }
                    ) { Text("Get My Location") }
                }
            }
        }
    }

    private fun isGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(
        client: com.google.android.gms.location.FusedLocationProviderClient,
        result: (LatLng) -> Unit
    ) {
        val cts = CancellationTokenSource()
        client.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cts.token
        ).addOnSuccessListener { loc ->
            result(LatLng(loc.latitude, loc.longitude))
        }
    }
}

@Composable
fun GoogleMapView(userLocation: LatLng?) {
    val defaultLocation = LatLng(10.762622, 106.660172)

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation ?: defaultLocation, 17f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp),
        cameraPositionState = cameraState,
        properties = MapProperties(isMyLocationEnabled = userLocation != null),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        userLocation?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Your location"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFullUI() {
    val mockLoc = LatLng(10.800000, 106.700000)
    Column {
        GoogleMapView(userLocation = mockLoc)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {}) { Text("Save Location") }
        Button(onClick = {}) { Text("Find My Car") }
        Text("Khoảng cách tới xe: 2.35 km")
        Button(onClick = {}) { Text("Get My Location") }
    }
}
