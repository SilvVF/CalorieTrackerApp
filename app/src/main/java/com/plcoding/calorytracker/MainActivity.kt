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
import com.plcoding.onboarding_presentation.activity.ActivityScreen
import com.plcoding.onboarding_presentation.age.AgeScreen
import com.plcoding.onboarding_presentation.gender.GenderScreen
import com.plcoding.onboarding_presentation.goal.GoalScreen
import com.plcoding.onboarding_presentation.height.HeightScreen
import com.plcoding.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.plcoding.onboarding_presentation.weight.WeightScreen
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
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.WEIGHT){
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.NUTRIENT_GOAL){
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.ACTIVITY){
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.GOAL){
                            GoalScreen(onNavigate = navController::navigate)
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