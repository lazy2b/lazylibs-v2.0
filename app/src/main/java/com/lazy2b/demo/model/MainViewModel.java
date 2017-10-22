package com.lazy2b.demo.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lazy2b on 17/10/22.
 */

public class MainViewModel extends AndroidViewModel {

    private static MainViewModel inst = null;

    public static MainViewModel get(AppCompatActivity act) {
        if (inst == null) {
            inst = ViewModelProviders.of(act).get(MainViewModel.class);
        }
        return inst;
    }

    private final MutableLiveData<MainLvItemModel> selected = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<MainLvItemModel>> datas;

    public void select(MainLvItemModel item) {
        selected.setValue(item);
    }

    public void select(int position) {
        select(getItem(position));
    }

    public LiveData<MainLvItemModel> getSelected() {
        return selected;
    }

    public MainLvItemModel getItem(int position) {
        return datas.getValue().get(position);
    }

    public MutableLiveData<MainLvItemModel> updState(String title, int state) {
        for (MainLvItemModel item : datas.getValue()) {
            if (item.title.equals(title)) {
                item.state = state;
            }
        }
        return selected;
    }

    public List<MainLvItemModel> initItems() {
        return new ArrayList<MainLvItemModel>() {
            {
                for (int i = 0; i < 30; i++) {
                    add(new MainLvItemModel("title" + i, 0));
                }
            }
        };
    }

    public LiveData<List<MainLvItemModel>> getItems() {
        if (datas == null) {
            datas = new MutableLiveData<List<MainLvItemModel>>();
            datas.setValue(initItems());
        }
        return datas;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        datas = null;
        inst = null;
    }
}


