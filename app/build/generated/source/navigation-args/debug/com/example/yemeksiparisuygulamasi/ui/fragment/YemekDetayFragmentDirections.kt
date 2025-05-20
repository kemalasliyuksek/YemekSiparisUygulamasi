package com.example.yemeksiparisuygulamasi.ui.fragment

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.yemeksiparisuygulamasi.R

public class YemekDetayFragmentDirections private constructor() {
  public companion object {
    public fun actionYemekDetayFragmentToSepetFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_yemekDetayFragment_to_sepetFragment)
  }
}
