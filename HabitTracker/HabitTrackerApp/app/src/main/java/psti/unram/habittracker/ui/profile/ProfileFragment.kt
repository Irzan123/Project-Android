package psti.unram.habittracker.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import psti.unram.habittracker.ViewModelFactory.AuthViewModelFactory
import psti.unram.habittracker.databinding.FragmentProfileBinding
import psti.unram.habittracker.ui.login.LoginActivity
import psti.unram.habittracker.ui.welcome.WelcomeActivity
import psti.unram.habittracker.utils.Utils

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvEmail = binding.tvEmail
        val tvUsername = binding.tvUsername

        tvEmail.text = Utils.userEmail
        tvUsername.text = Utils.userName

        setupAction()
    }

    private fun setupAction() {
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(context, WelcomeActivity::class.java))
        }
    }
}