from masadaML.database.DbDataprovider import get_price_history
import pandas as pd


def start_analyze(symbol, from_date, to_date):
    dataset = get_price_history(from_date=from_date,
                                to_date=to_date,
                                symbol=symbol)
    dataframe = pd.DataFrame(dataset)
    print(dataframe.keys())
