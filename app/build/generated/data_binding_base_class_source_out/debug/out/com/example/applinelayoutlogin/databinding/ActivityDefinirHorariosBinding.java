// Generated by view binder compiler. Do not edit!
package com.example.applinelayoutlogin.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import com.example.applinelayoutlogin.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityDefinirHorariosBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  private ActivityDefinirHorariosBinding(@NonNull ConstraintLayout rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDefinirHorariosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDefinirHorariosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_definir_horarios, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDefinirHorariosBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new ActivityDefinirHorariosBinding((ConstraintLayout) rootView);
  }
}
