package ch.ost.rj.mge.u06.mailer.fragments;

public interface LoginInputFragmentCallback {
    void onInputChanged(String email, String password, boolean keepData, boolean inputsAreValid);
}
