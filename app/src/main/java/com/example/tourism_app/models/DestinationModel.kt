package com.example.tourism_app.models

import android.net.Uri

data class DestinationModel(
    var destinationId: String? = null,
    var destinationName: String? = null,
    var destinationLocation: String? = null,
    var destinationDescription: String? = null,
    var destinationImg: String? = null,
)
