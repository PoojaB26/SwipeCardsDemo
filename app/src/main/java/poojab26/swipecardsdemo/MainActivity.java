package poojab26.swipecardsdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static DataModelAdapter dataModelAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<DataModel> dataArray;
    private SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        dataArray = new ArrayList<>();
        dataArray.add(new DataModel("http://lorempixel.com/output/food-q-c-640-480-8.jpg", "Japanese" ));
        dataArray.add(new DataModel("http://lorempixel.com/output/food-q-c-640-480-9.jpg", "Burgers" ));
        dataArray.add(new DataModel("http://lorempixel.com/output/food-q-c-640-480-1.jpg", "Gravy" ));
        dataArray.add(new DataModel("https://s-media-cache-ak0.pinimg.com/originals/91/e2/3e/91e23e865279a0d8a6ea23a87dea640c--homemade-mozzarella-sticks-mozzarella-cheese-sticks.jpg", "Cheese Sticks" ));
        dataArray.add(new DataModel("https://s-media-cache-ak0.pinimg.com/originals/a9/32/d5/a932d5203dc1b033380af7a16086d1f2.jpg", "Steaks" ));

        dataModelAdapter = new DataModelAdapter(dataArray, MainActivity.this);
        flingContainer.setAdapter(dataModelAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Log.d("left", dataArray.get(0).getDescription());
                dataArray.remove(0);
                dataModelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                dataArray.remove(0);
                dataModelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                dataModelAdapter.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class DataModelAdapter extends BaseAdapter {


        public List<DataModel> dataModelList;
        public Context context;

        private DataModelAdapter(List<DataModel> apps, Context context) {
            this.dataModelList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return dataModelList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(dataModelList.get(position).getDescription() + "");

            Glide.with(MainActivity.this).load(dataModelList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }
}
