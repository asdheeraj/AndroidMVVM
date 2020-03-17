package com.dheeraj.samplemvvm.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dheeraj.samplemvvm.R.layout

/**
 * A View class (Activity class) to display the Dashboard functionality to the User
 */
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_dashboard)
    }
}
