package ch.ost.rj.mge.u04.mailer.fragments;

public interface LoginInputFragmentCallback {
    void onInputChanged(String email, String password, boolean inputsAreValid);
}
