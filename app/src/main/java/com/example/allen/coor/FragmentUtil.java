package com.example.allen.coor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager.BackStackEntry;

public class FragmentUtil {

	public static void startTabFragment(FragmentManager manager, Class<? extends Fragment> clazz, int widgetId, Bundle bundle, boolean addFragmentToStack, int popUpInclusive) {
		if (clazz != null && manager != null) {
			int backStackCount = manager.getBackStackEntryCount();
			for (int i = 0; i < backStackCount; i++) {
				int backStackId = manager.getBackStackEntryAt(i).getId();

				manager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
			startFragment(manager, clazz, widgetId, bundle, addFragmentToStack, popUpInclusive);
		}
	}

	public static void startTabFragment(FragmentManager manager, Fragment fragment, int widgetId, Bundle bundle, boolean addFragmentToStack, int popUpInclusive) {
		if (fragment != null && manager != null) {
			int backStackCount = manager.getBackStackEntryCount();
			for (int i = 0; i < backStackCount; i++) {
				int backStackId = manager.getBackStackEntryAt(i).getId();

				manager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
			startFragment(manager, fragment, widgetId, bundle, addFragmentToStack, popUpInclusive);
		}
	}

	public static void startFragment(FragmentManager manager, Class<? extends Fragment> clazz, int widgetId, Bundle bundle, boolean addFragmentToStack, int popUpInclusive) {
		if (clazz != null && manager != null) {
			Fragment targetFragment = manager.findFragmentByTag(clazz.getName());
			if (popUpInclusive == FragmentManager.POP_BACK_STACK_INCLUSIVE && targetFragment != null) {
				manager.popBackStack(targetFragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}

			try {
				targetFragment = clazz.newInstance();
				if (bundle != null) {
					targetFragment.setArguments(bundle);
				}
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(widgetId, targetFragment, clazz.getName());
				if (addFragmentToStack) {
					transaction.addToBackStack(clazz.getName());
				}
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** 新增一個頁面 */
	public static void startFragment(FragmentManager manager, Class<? extends Fragment> clazz, int widgetId, Bundle bundle, boolean addFragmentToStack) {
		if (clazz != null && manager != null) {
			Fragment targetFragment = manager.findFragmentByTag(clazz.getName());
			if (targetFragment == null) {
				try {
					targetFragment = clazz.newInstance();
					if (bundle != null) {
						targetFragment.setArguments(bundle);
					}
					FragmentTransaction transaction = manager.beginTransaction();
					transaction.replace(widgetId, targetFragment, clazz.getName());
					if (addFragmentToStack) {
						transaction.addToBackStack(clazz.getName());
					}
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				manager.popBackStack(targetFragment.getTag(), 0);
			}
		}
	}

	public static void startFragment(FragmentManager manager, Fragment targetFragment, int widgetId, Bundle bundle, boolean addFragmentToStack, int popUpInclusive) {
		if (targetFragment != null && manager != null) {
			if (popUpInclusive == FragmentManager.POP_BACK_STACK_INCLUSIVE && targetFragment != null) {
				manager.popBackStack(targetFragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}

			try {
				if (bundle != null) {
					targetFragment.setArguments(bundle);
				}
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(widgetId, targetFragment, targetFragment.getClass().getName());
				if (addFragmentToStack) {
					transaction.addToBackStack(targetFragment.getClass().getName());
				}
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startFragment(FragmentManager manager, Fragment targetFragment, int widgetId, Bundle bundle, boolean addFragmentToStack) {
		if (manager != null) {
			try {
				if (bundle != null) {
					targetFragment.setArguments(bundle);
				}
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(widgetId, targetFragment, targetFragment.getClass().getName());
				if (addFragmentToStack) {
					transaction.addToBackStack(targetFragment.getClass().getName());
				}
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startFragment(FragmentManager manager, Fragment targetFragment, int widgetId, boolean addFragmentToStack) {
		if (manager != null) {
			try {
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(widgetId, targetFragment, targetFragment.getClass().getName());
				if (addFragmentToStack) {
					transaction.addToBackStack(targetFragment.getClass().getName());
				}
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startFragmentAllowStateLoss(FragmentManager manager, Fragment targetFragment, int widgetId, boolean addFragmentToStack) {
		if (manager != null) {
			try {
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(widgetId, targetFragment, targetFragment.getClass().getName());
				if (addFragmentToStack) {
					transaction.addToBackStack(targetFragment.getClass().getName());
				}
				transaction.commitAllowingStateLoss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 結束Fragment
	 * 
	 * @param manager
	 */
	public static void finish(FragmentManager manager) {
		finish(manager, false);
	}

	/**
	 * 立即結束Fragment
	 * 
	 * @param manager
	 * @param isImmediate
	 */
	public static void finish(FragmentManager manager, boolean isImmediate) {
		if (manager == null) {
			return;
		}
		if (isImmediate) {
			manager.popBackStackImmediate();
		} else {
			manager.popBackStack();
		}
	}

	/**
	 * 取得目前最上層的Fragment
	 * 
	 * @param manager
	 * @return
	 */
	public static Fragment getTopFragment(FragmentManager manager) {
		int count = manager.getBackStackEntryCount();
		if (count > 0) {
			BackStackEntry entry = manager.getBackStackEntryAt(count - 1);
			String name = entry.getName();
			Fragment topFragment = manager.findFragmentByTag(name);
			return topFragment;
		}
		return null;
	}
}
