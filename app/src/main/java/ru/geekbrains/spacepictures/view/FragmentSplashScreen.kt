import android.media.MediaPlayer
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentSplashScreenBinding
import ru.geekbrains.spacepictures.util.SOUND_INTRO
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import ru.geekbrains.spacepictures.util.packageString
import ru.geekbrains.spacepictures.view.api.POD.PictureOfTheDayFragment

class FragmentSplashScreen :
    ViewBindingFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    override fun onStart() {
        super.onStart()

        val player = MediaPlayer()
        with(player) {
            val fl = android.net.Uri.parse(packageString + SOUND_INTRO)
            setDataSource(requireContext(), fl)
            prepare()
            start()
            setOnCompletionListener {
                release()
                showMenu()
            }
        }

        binding.logo.setOnClickListener {
            binding.logo.animate().cancel()
            showMenu()
        }
        val logoScale = binding.logo.scaleX
        binding.logo.animate()

        binding.logo.animate()
            .setDuration(1500)
            .scaleX(logoScale + 0.05f)
            .scaleY(logoScale + 0.05f)
            .alpha(1f)
            .withEndAction {
                binding.logo.animate()
                    .setDuration(1500)
                    .scaleX(logoScale + 0.1f)
                    .scaleY(logoScale + 0.1f)
                    .alpha(0f)
                    .withEndAction { showMenu() }
            }
    }

    private fun showMenu() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PictureOfTheDayFragment.newInstance())
            .addToBackStack("menu")
            .commit()
    }


}