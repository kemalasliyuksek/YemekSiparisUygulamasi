package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.`data`.model.Yemek
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class AnasayfaFragmentDirections private constructor() {
  private data class ActionAnasayfaFragmentToYemekDetayFragment(
    public val yemek: Yemek,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_anasayfaFragment_to_yemekDetayFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Yemek::class.java)) {
          result.putParcelable("yemek", this.yemek as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(Yemek::class.java)) {
          result.putSerializable("yemek", this.yemek as Serializable)
        } else {
          throw UnsupportedOperationException(Yemek::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionAnasayfaFragmentToYemekDetayFragment(yemek: Yemek): NavDirections =
        ActionAnasayfaFragmentToYemekDetayFragment(yemek)

    public fun actionAnasayfaFragmentToSepetFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_anasayfaFragment_to_sepetFragment)
  }
}
