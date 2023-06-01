package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.applinelayoutlogin.libs.varGlobals;

public class principal extends AppCompatActivity {

    protected principal activity;
    protected int v_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setTitle("Bolita - Principal");
        this.activity = this;
    }

    public void f_mostrar_multiple() {
        Intent linker;
        linker = new Intent(activity, user_multiple_jugadas.class);
        startActivity( linker );
    }
   //      Método para mostrar y ocultar Menu
   @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menuapp, m);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu m) {
        MenuItem admin, lister;
        admin = m.findItem(0);
        if( varGlobals.nameGrupoUsr.compareTo("listero") == 0) {
            admin.setVisible(false);
        }

        return true;
    }

    //    Método para asignar las funciones correspondientes a las opciones
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public boolean onOptionsItemSelected(MenuItem i) {
        Intent linker;
        switch (i.getItemId()) {
            case R.id.idItem_Admin_Horarios:
                linker = new Intent(activity, definirHorarios.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Menu_Crear:
                linker = new Intent(activity, crearMenu.class);
                startActivity( linker );
            break;
            case R.id.idItem_Admin_Menu_Modificar:
                Toast.makeText(activity, "Click Modificar Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Menu_Eliminar:
                Toast.makeText(activity, "Click Eliminar Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_SubMenu_Crear:
                Intent vSgte_crearSubMenu = new Intent(activity, agregarSubMenu.class);
                startActivity( vSgte_crearSubMenu );
                break;
            case R.id.idItem_Admin_SubMenu_Modificar:
                Toast.makeText( activity, "Modifica SubMenu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_SubMenu_Eliminar:
                Toast.makeText(activity, "Elimina SubMenu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Permiso_Crear:
                linker = new Intent(activity, crearPermisos.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Permiso_Modificar:
                Toast.makeText(activity, "Modificar Permiso3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Permiso_Eliminar:
                Toast.makeText( activity, "Eliminar Permiso", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Grupo_Crear:
                linker = new Intent(activity, crearGrupo.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Grupo_Asociar:
                linker = new Intent(activity, asociarPermisoGrupo.class);
                startActivity( linker );
                Toast.makeText(activity, "Asocoiar Permisos a grupo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Grupo_Modificar:
                Toast.makeText(activity, "Modificar Grupo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Grupo_Eliminar:
                Toast.makeText(activity, "Eliminar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Mostrar_TarifaPago:
                Toast.makeText( activity, "Mostrar Tarifa PAgo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Mostrar_Tiro:
                linker = new Intent(activity, mostrarTiro.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Mostrar_ListaListero:
                linker = new Intent(activity, mostararDatos.class);
                varGlobals.src = "mostararDatos";
                startActivity( linker );
                break;
            case R.id.idItem_Listero_MisList:
                linker = new Intent(activity, mostararDatos.class);
                varGlobals.src = "mostararList";
                startActivity( linker );
            break;
            case R.id.idItem_Listero_MisBotes:
                linker = new Intent(activity, mostararDatos.class);
                varGlobals.src = "mostararBotes";
                startActivity( linker );
            break;
            case R.id.idItem_Admin_Registrar_TarifaPago:
                linker = new Intent(activity, agregarTarifaPago.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Registrar_Tiro:
                Intent vSgte_crearRegistroTiro = new Intent(activity, registrartiro.class);
                startActivity( vSgte_crearRegistroTiro );
                break;
            case R.id.idItem_Admin_Usuario_Bloquear:
                linker = new Intent(activity, bloquearDesbloquerUsuario.class);
                varGlobals.src = "bloquear";
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Usuario_Desbloquear:
                linker = new Intent(activity, bloquearDesbloquerUsuario.class);
                varGlobals.src = "desbloquear";
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Usuario_Modificar:
                Toast.makeText(activity, "Modificar Usuario", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Admin_Usuario_Registrar:
                linker = new Intent(activity, crearUsuario.class);
                startActivity( linker );
                break;
            case R.id.idItem_Admin_Usuario_Eliminar:
                Toast.makeText(activity, "Eliminar Usuario", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idItem_Listero_ShowClose:
                linker = new Intent(activity, verResumen.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_GralTiro:
                linker = new Intent(activity, registrartiro.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Simple:
                linker = new Intent(activity, user_jugada_simple_Activity.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Parlet:
                linker = new Intent(activity, user_parlet_activity.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Candado:
                linker = new Intent(activity, user_candado_parlet.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Multiple:
                linker = new Intent(activity, user_multiple_jugadas.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Centena:
                linker = new Intent(activity, insertarCentena.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Decena:
                linker = new Intent(activity, insertarDecenas.class);
                startActivity( linker );
                break;
            case R.id.idItem_Listero_Terminal:
                linker = new Intent(activity, insertarTerminal.class);
                startActivity( linker );
                break;

        }

        return super.onOptionsItemSelected(i);
    }

}