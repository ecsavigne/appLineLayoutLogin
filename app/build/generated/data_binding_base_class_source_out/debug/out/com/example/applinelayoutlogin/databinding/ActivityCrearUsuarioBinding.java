// Generated by view binder compiler. Do not edit!
package com.example.applinelayoutlogin.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import android.viewbinding.ViewBindings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.applinelayoutlogin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCrearUsuarioBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button idBtnRegusrGuardar;

  @NonNull
  public final EditText idEtxtRegusrPass;

  @NonNull
  public final EditText idEtxtRegusrPassR;

  @NonNull
  public final EditText idEtxtUsrRegusr;

  @NonNull
  public final Spinner idSpnRegusr;

  @NonNull
  public final ConstraintLayout idTxtVieRegusr;

  @NonNull
  public final TextView idTxtvBloqReloj;

  private ActivityCrearUsuarioBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button idBtnRegusrGuardar, @NonNull EditText idEtxtRegusrPass,
      @NonNull EditText idEtxtRegusrPassR, @NonNull EditText idEtxtUsrRegusr,
      @NonNull Spinner idSpnRegusr, @NonNull ConstraintLayout idTxtVieRegusr,
      @NonNull TextView idTxtvBloqReloj) {
    this.rootView = rootView;
    this.idBtnRegusrGuardar = idBtnRegusrGuardar;
    this.idEtxtRegusrPass = idEtxtRegusrPass;
    this.idEtxtRegusrPassR = idEtxtRegusrPassR;
    this.idEtxtUsrRegusr = idEtxtUsrRegusr;
    this.idSpnRegusr = idSpnRegusr;
    this.idTxtVieRegusr = idTxtVieRegusr;
    this.idTxtvBloqReloj = idTxtvBloqReloj;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCrearUsuarioBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCrearUsuarioBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_crear_usuario, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCrearUsuarioBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.id_btn_regusr_guardar;
      Button idBtnRegusrGuardar = ViewBindings.findChildViewById(rootView, id);
      if (idBtnRegusrGuardar == null) {
        break missingId;
      }

      id = R.id.id_etxt_regusr_pass;
      EditText idEtxtRegusrPass = ViewBindings.findChildViewById(rootView, id);
      if (idEtxtRegusrPass == null) {
        break missingId;
      }

      id = R.id.id_etxt_regusr_passR;
      EditText idEtxtRegusrPassR = ViewBindings.findChildViewById(rootView, id);
      if (idEtxtRegusrPassR == null) {
        break missingId;
      }

      id = R.id.id_etxt_usr_regusr;
      EditText idEtxtUsrRegusr = ViewBindings.findChildViewById(rootView, id);
      if (idEtxtUsrRegusr == null) {
        break missingId;
      }

      id = R.id.id_spn_regusr;
      Spinner idSpnRegusr = ViewBindings.findChildViewById(rootView, id);
      if (idSpnRegusr == null) {
        break missingId;
      }

      ConstraintLayout idTxtVieRegusr = (ConstraintLayout) rootView;

      id = R.id.id_txtv_bloq_reloj;
      TextView idTxtvBloqReloj = ViewBindings.findChildViewById(rootView, id);
      if (idTxtvBloqReloj == null) {
        break missingId;
      }

      return new ActivityCrearUsuarioBinding((ConstraintLayout) rootView, idBtnRegusrGuardar,
          idEtxtRegusrPass, idEtxtRegusrPassR, idEtxtUsrRegusr, idSpnRegusr, idTxtVieRegusr,
          idTxtvBloqReloj);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
