package udemy.fausto.com.t_permissiondemo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

// https://www.youtube.com/watch?v=MG8KtxaCKJ8

private const val FILE_PICKER_ID = 12
private const val PERMISSION_REQUEST = 10


class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private var permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        btn_request.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkPermission(this, permissions)) {
                    Toast.makeText(this, "Permission already provided", Toast.LENGTH_LONG).show()

                    } else {  // I don´t have permissions yet and new version

                        requestPermissions(permissions, PERMISSION_REQUEST)
                    }
            }

            else {  // is an old SDK
                Toast.makeText(this, "Permission already provided", Toast.LENGTH_LONG).show()
            }
        }


        btn_camera.setOnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)

        }

        btn_file.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"

            startActivityForResult(intent, FILE_PICKER_ID)

        }





    }

    fun checkPermission(context: Context, permissionArray: Array<String>) : Boolean {




        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true

            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false



                    var requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    

                }
            }

        }

    }


}
