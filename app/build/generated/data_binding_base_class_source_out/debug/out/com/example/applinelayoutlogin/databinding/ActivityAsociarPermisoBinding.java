// Generated by view binder compiler. Do not edit!
package com.example.applinelayoutlogin.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import android.viewbinding.ViewBindings;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.example.applinelayoutlogin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAsociarPermisoBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText editTextTextMultiLine;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final ImageButton imageButton2;

  @NonNull
  public final Spinner spinner;

  private ActivityAsociarPermisoBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText editTextTextMultiLine, @NonNull ImageButton imageButton,
      @NonNull ImageButton imageButton2, @NonNull Spinner spinner) {
    this.rootView = rootView;
    this.editTextTextMultiLine = editTextTextMultiLine;
    this.imageButton = imageButton;
    this.imageButton2 = imageButton2;
    this.spinner = spinner;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAsociarPermisoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAsociarPermisoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_asociar_permiso, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAsociarPermisoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextTextMultiLine;
      EditText editTextTextMultiLine = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextMultiLine == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.imageButton2;
      ImageButton imageButton2 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton2 == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = ViewBindings.findChildViewById(rootView, id);
      if (spinner == null) {
        break missingId;
      }

      return new ActivityAsociarPermisoBinding((RelativeLayout) rootView, editTextTextMultiLine,
          imageButton, imageButton2, spinner);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}