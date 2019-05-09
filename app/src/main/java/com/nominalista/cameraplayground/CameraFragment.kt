package com.nominalista.cameraplayground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment() {

    private val preview by lazy {
        val configuration = PreviewConfig.Builder().build()
        Preview(configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CameraX.bindToLifecycle(this, preview)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_gallery.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, GalleryFragment())
                .addToBackStack("GalleryFragment")
                .commit()
        }

        preview.setOnPreviewOutputUpdateListener { texture_view.surfaceTexture = it.surfaceTexture }
    }
}