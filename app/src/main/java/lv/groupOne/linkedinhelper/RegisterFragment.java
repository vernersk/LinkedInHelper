package lv.groupOne.linkedinhelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterFragment extends Fragment implements View.OnClickListener{

    EditText name;
    EditText email;
    EditText password;
    Button signUp;
    FirebaseAuth fAuth;
    View view;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(getActivity(), PrimaryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        name = view.findViewById(R.id.txtName);
        email = view.findViewById(R.id.txtEmail);
        password = view.findViewById(R.id.txtPassword);
        signUp = view.findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView signIn = requireActivity().findViewById(R.id.lblRegisterSignIn);
        signIn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container);
            setFragment(new LoginFragment());
        });
    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager;
        fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSignUp){

            String inputName = name.getText().toString().trim();
            String inputEmail = email.getText().toString().trim();
            String inputPassword = password.getText().toString().trim();

            if(TextUtils.isEmpty(inputName)){
                name.setError("Name is required");
                return;
            }

            if(TextUtils.isEmpty(inputEmail)){
                email.setError("Email is required");
                return;
            }

            if(TextUtils.isEmpty(inputPassword)){
                password.setError("Password must be at least 8 characters long");
                return;
            }

            fAuth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(requireContext().getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), PrimaryActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(requireContext().getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}