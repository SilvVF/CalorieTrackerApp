package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.navigation.Route
import com.plcoding.onboarding_presentation.age.AgeScreen
import com.plcoding.onboarding_presentation.gender.GenderScreen
import com.plcoding.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint //need to tell hilt that it can see this
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController() // nav controller needed for nav in compose
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost( //nav host with start destination for the app
                        navController = navController,
                        startDestination = Route.WELCOME // start at this screen
                    ){ //define the composables that will be part of the nav graph
                        composable(route = Route.WELCOME){
                            //important to use the right navigate
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.AGE){
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.GENDER){
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.HEIGHT){

                        }
                        composable(route = Route.WEIGHT){

                        }
                        composable(route = Route.NUTRIENT_GOAL){

                        }
                        composable(route = Route.ACTIVITY){

                        }
                        composable(route = Route.GOAL){

                        }
                        composable(route = Route.TRACKER_OVERVIEW){

                        }
                        composable(route = Route.SEARCH){

                        }

                    }
                }

            }
        }
    }
}