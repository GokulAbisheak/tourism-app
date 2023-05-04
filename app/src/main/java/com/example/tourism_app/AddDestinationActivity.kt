import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddDestinationActivity : AppCompatActivity() {
    private lateinit var destinationNameInput: EditText
    private lateinit var destinationLocationInput: EditText
    private lateinit var destinationDescriptionInput: EditText
    private lateinit var destinationImageButton: Button
    private lateinit var addDestinationButton: Button

    private var imageUri: Uri? = null
    private lateinit var storageRef: StorageReference
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destination)

        destinationNameInput = findViewById(R.id.destination_name_input)
        destinationLocationInput = findViewById(R.id.destination_location_input)
        destinationDescriptionInput = findViewById(R.id.destination_description_input)
        destinationImageButton = findViewById(R.id.destination_image_button)
        addDestinationButton = findViewById(R.id.add_destination_button)

        storageRef = FirebaseStorage.getInstance().getReference("destination_images")
        databaseRef = FirebaseDatabase.getInstance().getReference("destinations")

        destinationImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        addDestinationButton.setOnClickListener {
            saveDestinationData()
        }
    }

    private fun saveDestinationData() {
        val destinationName = destinationNameInput.text.toString().trim()
        val destinationLocation = destinationLocationInput.text.toString().trim()
        val destinationDescription = destinationDescriptionInput.text.toString().trim()

        if (destinationName.isEmpty() || destinationLocation.isEmpty() || destinationDescription.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (imageUri == null) {
            Toast.makeText(this, "Please choose an image", Toast.LENGTH_SHORT).show()
            return
        }

        val imageRef = storageRef.child("${System.currentTimeMillis()}.${imageUri!!.pathSegments.last()}")
        imageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                imageRef.downloadUrl
                    .addOnSuccessListener { uri ->
                        val destination = Destination(destinationName, destinationLocation, destinationDescription, uri.toString())
                        databaseRef.push().setValue(destination)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Destination added successfully", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to add destination: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageUri = data?.data
            destinationImageButton.text = "Image Selected"
        }
    }
}
