/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.psdev.licensesdialog.LicensesDialog
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)

        textViewVersion.text = getVersion(this)
        buttonLicenses.setOnClickListener {
            LicensesDialog.Builder(this).setNotices(R.raw.notices).build().show()
        }
    }

    private fun getVersion(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val resources = context.resources
            return String.format(resources.getString(R.string.about_versionInfo), packageInfo.versionName)
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return context.getString(R.string.all_unknown)
    }
}
