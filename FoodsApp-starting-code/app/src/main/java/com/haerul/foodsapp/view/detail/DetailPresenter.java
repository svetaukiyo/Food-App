package com.haerul.foodsapp.view.detail;

import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.model.Meals;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealById(String mealName) {
        view.showLoading();
        Utils.getApi().getMealByName(mealName).enqueue(
                new Callback<Meals>() {
                    @Override
                    public void onResponse(@NotNull Call<Meals> call,@NotNull Response<Meals> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setMeal(response.body().getMeals().get(0));
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<Meals> call,@NotNull Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                }
        );
    }
}
