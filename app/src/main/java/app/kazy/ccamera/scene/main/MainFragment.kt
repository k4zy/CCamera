package app.kazy.ccamera.scene.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.kazy.ccamera.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val adapter = ImageAdapter { image ->
        viewModel.saveImage(requireContext(), image.url)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.composeView.setContent {
            MaterialTheme {
                Text("Hello Compose!")
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.recyclerView.adapter = adapter
//        viewModel.search("music")
//        viewModel.images.observe(viewLifecycleOwner, { images ->
//            adapter.images = images
//        })
    }
}
