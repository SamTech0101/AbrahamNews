package com.example.abrahamnews.base;

import android.content.Context;

public interface BaseView {
void setProgressIndicator(boolean mustShow);
void showError(String errorMessage);
Context context();

}
