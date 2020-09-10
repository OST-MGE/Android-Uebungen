package ch.ost.rj.mge.u05.mailer.fragments;

public interface LoginInputFragmentCallback {
    void onInputChanged(String email, String password, boolean keepData, boolean inputsAreValid);
}
