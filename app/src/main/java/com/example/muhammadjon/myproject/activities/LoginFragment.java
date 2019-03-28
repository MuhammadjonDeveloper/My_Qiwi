//package com.example.muhammadjon.myproject.activities;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TextInputEditText;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.AppCompatButton;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import io.reactivex.disposables.CompositeDisposable;
//import uz.uzdevs.planner.R;
//import uz.uzdevs.planner.common.IConfig;
//import uz.uzdevs.planner.common.IWatcher;
//import uz.uzdevs.planner.common.TextWatcherImpl;
//import uz.uzdevs.planner.network.ApiService;
//
//public class LoginFragment extends Fragment implements IWatcher, View.OnClickListener {
//    private TextInputEditText etlogin, etpasswort;
//    private AppCompatButton btnsave, btnsign;
//    private IConfig listener;
//    private String login, passwort;
//    private ApiService service;
//    private CompositeDisposable cd = new CompositeDisposable();
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.login_fragment, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        if (getContext() instanceof IConfig) {
//            listener = (IConfig) getContext();
//        }
//
//
//        etlogin = view.findViewById(R.id.login_f_login);
//        etpasswort = view.findViewById(R.id.login_f_passwort);
//
//        btnsave = view.findViewById(R.id.login_f_btn);
//        btnsign = view.findViewById(R.id.login_f_sing_up_btn);
//        btnsave.setEnabled(false);
//        btnsave.setOnClickListener(this);
//        btnsign.setOnClickListener(this);
//
//        etlogin.addTextChangedListener(new TextWatcherImpl(this));
//        etpasswort.addTextChangedListener(new TextWatcherImpl(this));
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.login_f_btn: {
//                listener.onClicked("save");
//                if (Utils.isOnline(getContext())) {
//                    SignInRequest signInRequest = new
//                            SignInRequest(login, passwort);
//                    Single<SignInResponse> sign = service.signIn(signInRequest);
//                    sign.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new SingleObserver<SignInResponse>() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                    cd.add(d);
//                                }
//
//                                @Override
//                                public void onSuccess(SignInResponse signInResponse) {
//                                    listener.onClicked("save");
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Toast.makeText(getContext(), "Something words: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                    /*Call<SignInResponse> call = service.signIn(signInRequest);
//                    call.enqueue(new Callback<SignInResponse>() {
//                        @Override
//                        public void onResponse(Call<SignInResponse> call,
//                                               Response<SignInResponse> response) {
//                            if (response.isSuccessful()) {
//                                listener.onClicked("save");
//                            } else {
//                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<SignInResponse> call, Throwable t) {
//                            Toast.makeText(getContext(), "Something words", Toast.LENGTH_SHORT).show();
//                        }
//                    });*/
//
//           /* } else{
//                Toast.makeText(getContext(), "Some error", Toast.LENGTH_SHORT).show();
//            }*/
//
//
//            }
//            break;
//            case R.id.login_f_sing_up_btn:
//                listener.onClicked("register");
//        }
//
//    }
//
//    @Override
//    public void onTextChanged(String text) {
//        login = etlogin.getText().toString();
//        passwort = etpasswort.getText().toString();
//        if (!login.isEmpty() && !passwort.isEmpty()) {
//            btnsave.setEnabled(true);
//        } else {
//            btnsave.setEnabled(false);
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (cd != null && cd.isDisposed()) {
//            cd.dispose();
//        }
//    }
//}
