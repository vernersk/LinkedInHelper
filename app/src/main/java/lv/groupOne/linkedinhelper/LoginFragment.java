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

public class LoginFragment extends Fragment implements View.OnClickListener{

    EditText loginEmail;
    EditText loginPassword;
    Button signIn;
    FirebaseAuth fAuth;
    View view;

    public LoginFragment() {
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
        view = inflater.inflate(R.layout.fragment_login, container, false);
        loginEmail = view.findViewById(R.id.txtLoginEmail);
        loginPassword = view.findViewById(R.id.txtLoginPassword);
        signIn = view.findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView signUp = requireActivity().findViewById(R.id.lblLoginSignUp);
        signUp.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container);
            setFragment(new RegisterFragment());
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
        Toast.makeText(requireContext().getApplicationContext(), "sign in clicked", Toast.LENGTH_LONG).show();
        if(v.getId() == R.id.btnSignIn){
            String inputEmail = loginEmail.getText().toString().trim();
            String inputPassword = loginPassword.getText().toString().trim();

            if(TextUtils.isEmpty(inputEmail)){
                loginEmail.setError("Email is required");
                return;
            }

            if(TextUtils.isEmpty(inputPassword)){
                loginPassword.setError("Password must be at least 8 characters long");
                return;
            }

            fAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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