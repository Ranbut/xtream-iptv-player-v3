package z.xtreamiptv.playerv3.view.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.ViewGroup;
import z.xtreamiptv.playerv3.model.LiveStreamCategoryIdDBModel;
import z.xtreamiptv.playerv3.view.fragment.TVArchiveFragment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVArchiveCategoriesAdapter extends FragmentPagerAdapter {
    private String[] LiveStreamCategoriesIds;
    private Context context;
    private String[] liveStreamCategoriesNames;
    final int liveStreamTotalCategories;
    private FragmentManager mFragmentManager;
    private Map<Integer, String> mFragmentTags = new HashMap();

    public TVArchiveCategoriesAdapter(List<LiveStreamCategoryIdDBModel> liveStreamCategories, FragmentManager fm, Context context) {
        super(fm);
        this.mFragmentManager = fm;
        this.liveStreamTotalCategories = liveStreamCategories.size();
        this.liveStreamCategoriesNames = new String[this.liveStreamTotalCategories];
        this.LiveStreamCategoriesIds = new String[this.liveStreamTotalCategories];
        this.context = context;
        for (int i = 0; i < this.liveStreamTotalCategories; i++) {
            String categoryName = ((LiveStreamCategoryIdDBModel) liveStreamCategories.get(i)).getLiveStreamCategoryName();
            String categoryId = ((LiveStreamCategoryIdDBModel) liveStreamCategories.get(i)).getLiveStreamCategoryID();
            this.liveStreamCategoriesNames[i] = categoryName;
            this.LiveStreamCategoriesIds[i] = categoryId;
        }
    }

    public int getCount() {
        return this.liveStreamTotalCategories;
    }

    public Fragment getItem(int position) {
        return TVArchiveFragment.newInstance(this.LiveStreamCategoriesIds[position]);
    }

    public CharSequence getPageTitle(int position) {
        return this.liveStreamCategoriesNames[position];
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Fragment obj = (Fragment) super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            this.mFragmentTags.put(Integer.valueOf(position), obj.getTag());
        }
        return obj;
    }

    public TVArchiveFragment getFragment(int position) {
        String tag = (String) this.mFragmentTags.get(Integer.valueOf(position));
        if (tag == null) {
            return null;
        }
        return (TVArchiveFragment) this.mFragmentManager.findFragmentByTag(tag);
    }
}
