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

public final class ActivityCrearGrupoBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText idETxtNombreGrupo;

  private ActivityCrearGrupoBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText idETxtNombreGrupo) {
    this.rootView = rootView;
    this.idETxtNombreGrupo = idETxtNombreGrupo;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCrearGrupoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCrearGrupoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_crear_grupo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCrearGrupoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.idETxt_NombreGrupo;
      EditText idETxtNombreGrupo = ViewBindings.findChildViewById(rootView, id);
      if (idETxtNombreGrupo == null) {
        break missingId;
      }

      return new ActivityCrearGrupoBinding((RelativeLayout) rootView, idETxtNombreGrupo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}