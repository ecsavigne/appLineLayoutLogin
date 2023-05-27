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
import android.widget.RelativeLayout;
import com.example.applinelayoutlogin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCrearMenuBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText idETxtNombreMenu;

  private ActivityCrearMenuBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText idETxtNombreMenu) {
    this.rootView = rootView;
    this.idETxtNombreMenu = idETxtNombreMenu;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCrearMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCrearMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_crear_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCrearMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.idETxt_NombreMenu;
      EditText idETxtNombreMenu = ViewBindings.findChildViewById(rootView, id);
      if (idETxtNombreMenu == null) {
        break missingId;
      }

      return new ActivityCrearMenuBinding((RelativeLayout) rootView, idETxtNombreMenu);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
