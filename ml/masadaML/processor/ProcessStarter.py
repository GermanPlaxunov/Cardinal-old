from masadaML.database.DbDataprovider import get_price_history
import numpy as np
from sklearn.model_selection import train_test_split


def start_analyze(symbol, from_date, to_date):
    dataframe = get_price_history(from_date=from_date,
                                  to_date=to_date,
                                  symbol=symbol)

    return dataframe.size


def create_classification_trading_condition(dataframe):
    dataframe['open-close'] = dataframe['open'] - dataframe['close']
    dataframe['high-low'] = dataframe['high'] - dataframe['low']
    dataframe = dataframe.dropna()
    X = dataframe[['open-close', 'high-low']]
    Y = np.where(dataframe['close'].shift(-1) > dataframe['close'], 1, -1)
    return (X, Y)


def create_regression_trading_condition(dataframe):
    dataframe['open-cloose'] = dataframe['open'] - dataframe['close']
    dataframe['high-low'] = dataframe['high'] - dataframe['low']
    dataframe = dataframe.dropna()
    X = dataframe[['open-close', 'high-low']]
    Y = dataframe['close'].shift(-1) - dataframe['close']
    return (X, Y)


def train_split_group(X, Y, split_ratio=0.8):
    return train_test_split(X, Y, train_size=split_ratio,
                            shuffle=False)
