// Generated by view binder compiler. Do not edit!
package com.example.applinelayoutlogin.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import android.widget.RelativeLayout;
import com.example.applinelayoutlogin.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityMostrarListaListerosBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  private ActivityMostrarListaListerosBinding(@NonNull RelativeLayout rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMostrarListaListerosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMostrarListaListerosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_mostrar_lista_listeros, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMostrarListaListerosBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new ActivityMostrarListaListerosBinding((RelativeLayout) rootView);
  }
}