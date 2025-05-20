package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.yemeksiparisuygulamasi.`data`.model.Yemek
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class YemekDetayFragmentArgs(
  public val yemek: Yemek,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Yemek::class.java)) {
      result.set("yemek", this.yemek as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Yemek::class.java)) {
      result.set("yemek", this.yemek as Serializable)
    } else {
      throw UnsupportedOperationException(Yemek::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): YemekDetayFragmentArgs {
      bundle.setClassLoader(YemekDetayFragmentArgs::class.java.classLoader)
      val __yemek : Yemek?
      if (bundle.containsKey("yemek")) {
        if (Parcelable::class.java.isAssignableFrom(Yemek::class.java) ||
            Serializable::class.java.isAssignableFrom(Yemek::class.java)) {
          __yemek = bundle.get("yemek") as Yemek?
        } else {
          throw UnsupportedOperationException(Yemek::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__yemek == null) {
          throw IllegalArgumentException("Argument \"yemek\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"yemek\" is missing and does not have an android:defaultValue")
      }
      return YemekDetayFragmentArgs(__yemek)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): YemekDetayFragmentArgs {
      val __yemek : Yemek?
      if (savedStateHandle.contains("yemek")) {
        if (Parcelable::class.java.isAssignableFrom(Yemek::class.java) ||
            Serializable::class.java.isAssignableFrom(Yemek::class.java)) {
          __yemek = savedStateHandle.get<Yemek?>("yemek")
        } else {
          throw UnsupportedOperationException(Yemek::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__yemek == null) {
          throw IllegalArgumentException("Argument \"yemek\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"yemek\" is missing and does not have an android:defaultValue")
      }
      return YemekDetayFragmentArgs(__yemek)
    }
  }
}
