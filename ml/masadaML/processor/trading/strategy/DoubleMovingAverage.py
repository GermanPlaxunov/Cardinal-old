import numpy as np
import pandas as pd


def double_moving_average(financial_data, short_window, long_window):
    signals = pd.DataFrame(index=financial_data.index)
    signals['signal'] = 0.0
    signals['short_mavg'] = get_mavg(financial_data, short_window)
    signals['long_mavg'] = get_mavg(financial_data, long_window)
    signals['signal'][short_window:] = get_signal(signals, short_window)
    signals['orders'] = signals['signal'].diff() # 1 for buy; -1 to sell.
    return signals


def get_mavg(financial_data, window):
    return financial_data['close'].rolling(window=window,
                                           min_periods=1,
                                           center=False).mean()


def get_signal(signals, short_window):
    return np.where(signals['short_mavg'][short_window:] >
                    signals['long_mavg'][short_window:], 1.0, 0.0)
