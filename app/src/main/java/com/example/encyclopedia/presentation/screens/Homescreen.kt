package com.example.encyclopedia.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.OutlinedButtonDefaults
import com.example.encyclopedia.presentation.components.NewSearchBar
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Accent5
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.theme.Secondary15
import com.example.encyclopedia.presentation.theme.Secondary30
import androidx.compose.material3.OutlinedButton
import com.example.encyclopedia.presentation.components.AnimatedOutlinedButton
import com.example.encyclopedia.presentation.components.EncyclopediaCard


import com.example.encyclopedia.presentation.viewmodel.AppViewModel
import com.example.encyclopedia.presentation.viewmodel.UserViewModel

@Composable
fun Homescreen(onNavigateToInfo:(String)->Unit,
               onNavigateToQuiz: (String)->Unit,
               onNavigateToLogin:()->Unit,
               onNavigateToRegister:() -> Unit,
               onNavigateToUserResults:()->Unit,
               userViewModel: UserViewModel
) {
    val user  = userViewModel.currentUser
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .background(Accent10)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Card(
//                shape = RoundedCornerShape(30.dp),
//                modifier = Modifier.padding(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//            ) {
//                Text(
//                    text = "Encyclopedia",
//                    fontSize = 35.sp,
//                    modifier = Modifier
//                        .background(Main60)
//                        .padding(16.dp),
//                    color = Accent10
//                )
//            }
            EncyclopediaCard()
            //searchbar
            Column(modifier = Modifier.padding(8.dp)) {
                    if (user != null) {
                        Text(text = "Welcome back, ${user.username}!", fontSize = 20.sp)
                    } else {
                        Text(text = "Welcome to the app!", fontSize = 20.sp)
                    }

                Log.d("Homescreen", "Current user: $user")

            }
            Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(16.dp),

                    ) {
                        Text(
                            text = "Choose a category",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 20.sp
                        )
                        Button(
                            onClick = { onNavigateToInfo("Category:Mammal_common_names") },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Main60,
                                contentColor = Accent10
                            )
                        ) {
                            Text(
                                text = "Mammals",
                                fontSize = 18.sp
                            )
                        }
                        Button(
                            onClick = { onNavigateToInfo("Category:Bird_common_names") },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Main60,
                                contentColor = Accent10
                            )

                        ) {
                            Text(
                                text = "Birds",
                                fontSize = 18.sp
                            )
                        }
                        Button(
                            onClick = { onNavigateToInfo("Category:Reptile_common_names") },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Main60,
                                contentColor = Accent10
                            )
                        ) {
                            Text(
                                text = "Reptiles",
                                fontSize = 18.sp
                            )
                        }
                        Button(
                            onClick = { onNavigateToInfo("Category:Fish_common_names") },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Main60,
                                contentColor = Accent10
                            )
                        ) {
                            Text(
                                text = "Aquatic Animals",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Test your knowledge",
                    fontSize = 20.sp
                )
                //button to navigate to quiz
                Button(
                    onClick = { onNavigateToQuiz("Mammals") },
                    modifier = Modifier
                        .height(90.dp)
                        .width(300.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Main60,
                        contentColor = Accent10
                    )
                ) {
                    Text(
                        text = "Ready, set, quiz!",
                        fontSize = 30.sp
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
//                Button(
//                    onClick = {onNavigateToLogin()},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 32.dp),
//                    colors =  ButtonDefaults.outlinedButtonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = Main60
//                    ),
//                    border = BorderStroke(1.dp, Main60)
//                ) {
//                    Text(text = "Login")
//                }
                if(user!=null){
                    AnimatedOutlinedButton("Your previous results", onClick = onNavigateToUserResults)
                    AnimatedOutlinedButton("Log out", onClick = {userViewModel.logout()})
                }else{
                AnimatedOutlinedButton("Login", onClick = onNavigateToLogin)
                AnimatedOutlinedButton("Sign up", onClick = onNavigateToRegister)
                    }
//                Button(
//                    onClick = { onNavigateToRegister()},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 32.dp),
//                    colors = ButtonDefaults.outlinedButtonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = Main60
//                    ),
//                    border = BorderStroke(1.dp, Main60)
//                ) {
//                    Text(text = "Sign Up")
//                }
            }
        }
    }
}
