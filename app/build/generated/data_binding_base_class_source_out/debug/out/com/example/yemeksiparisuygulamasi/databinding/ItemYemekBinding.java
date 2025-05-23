// Generated by view binder compiler. Do not edit!
package com.example.yemeksiparisuygulamasi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.yemeksiparisuygulamasi.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemYemekBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imageViewYemek;

  @NonNull
  public final TextView textViewYemekAdi;

  @NonNull
  public final TextView textViewYemekFiyat;

  private ItemYemekBinding(@NonNull CardView rootView, @NonNull ImageView imageViewYemek,
      @NonNull TextView textViewYemekAdi, @NonNull TextView textViewYemekFiyat) {
    this.rootView = rootView;
    this.imageViewYemek = imageViewYemek;
    this.textViewYemekAdi = textViewYemekAdi;
    this.textViewYemekFiyat = textViewYemekFiyat;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemYemekBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemYemekBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_yemek, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemYemekBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageViewYemek;
      ImageView imageViewYemek = ViewBindings.findChildViewById(rootView, id);
      if (imageViewYemek == null) {
        break missingId;
      }

      id = R.id.textViewYemekAdi;
      TextView textViewYemekAdi = ViewBindings.findChildViewById(rootView, id);
      if (textViewYemekAdi == null) {
        break missingId;
      }

      id = R.id.textViewYemekFiyat;
      TextView textViewYemekFiyat = ViewBindings.findChildViewById(rootView, id);
      if (textViewYemekFiyat == null) {
        break missingId;
      }

      return new ItemYemekBinding((CardView) rootView, imageViewYemek, textViewYemekAdi,
          textViewYemekFiyat);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
