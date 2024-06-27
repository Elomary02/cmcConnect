package com.example.cmcconnect.shared.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cmcconnect.MainActivity
import com.example.cmcconnect.R
import com.example.cmcconnect.model.UserInInfo
import com.example.cmcconnect.shared.User.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var emailEt : EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button

    private val signInViewModel: SignInViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.emailEditText)
        passwordEt = findViewById(R.id.passwordEditText)
        loginBtn = findViewById(R.id.login_button)

        loginBtn.setOnClickListener {
            val userEmail = emailEt.text.toString()
            val userPassword = passwordEt.text.toString()

            signInViewModel.onSignIn(userEmail, userPassword)
        }
        viewModel.test.observe(this@LoginActivity, Observer {
                test->Log.d("testData","$test")
        })
        viewModel.getYear()
        observeSignInState()
    }

    private fun observeSignInState(){
        lifecycleScope.launchWhenStarted {
            signInViewModel.signInState.collect { state ->
                when (state) {
                    is SignInViewModel.SignInState.Success -> {
                        if (state.success) {
                            Log.d("LoginActivity", "Sign-In success")
                            /*signInViewModel.currentUserEmail.observe(this@LoginActivity, Observer { email ->
                                userViewModel.getUserInfo(email)
                                Log.d("userInfo","current user email $email")

                                userViewModel.userInfoLiveDate.observe(this@LoginActivity) { userIn ->
                                    UserInInfo.id = userIn.id
                                    Log.d("userInName","${UserInInfo.id_type_user_fk}")
                                }
                            })*/
                            signInViewModel.getCurrentUserEmail()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {

                        }

                    }

                    is SignInViewModel.SignInState.Error -> {}
                    is SignInViewModel.SignInState.Initial -> {}
                    is SignInViewModel.SignInState.Loading -> {}
                }
            }
        }
    }

}