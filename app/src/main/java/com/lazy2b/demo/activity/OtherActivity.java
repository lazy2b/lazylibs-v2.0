package com.lazy2b.demo.activity;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lazy2b.demo.R;
import com.lazy2b.demo.databinding.ActivityOtherBinding;
import com.lazy2b.demo.model.MainLvItemModel;
import com.lazy2b.demo.model.MainViewModel;

public class OtherActivity extends AppCompatActivity {
    int position = 4;

    MainViewModel viewModel;
    ActivityOtherBinding binding;
//    DataBind

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_other);
        viewModel = MainViewModel.get(this);
        viewModel.getSelected().observe(this, new Observer<MainLvItemModel>() {
            @Override
            public void onChanged(@Nullable MainLvItemModel itemModel) {
                binding.setWocao(itemModel);
            }
        });
//        position = getIntent().getIntExtra("position", position);
    }

    public void uState(View view) {
        viewModel.updState(viewModel.getSelected().getValue().title, 10086);
//        .observe(this, new Observer<MainLvItemModel>() {
//            @Override
//            public void onChanged(@Nullable MainLvItemModel itemModel) {
//                binding.setWocao(itemModel);
//            }
//        });
        Toast.makeText(this, "title->" + viewModel.getSelected().getValue().title, Toast.LENGTH_SHORT).show();
        MainActivity.inst.uState(position);
    }

}
