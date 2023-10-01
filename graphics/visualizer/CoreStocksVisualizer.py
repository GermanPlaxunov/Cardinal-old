from database.QueryProvider import get_all_core_stocks_query
from database.connection.ConnectionFactory import get_connection_cursor
from database.plot.Plotter import show_core_stocks
import pandas as pd


def visualize():
    query = get_all_core_stocks_query()
    cursor = get_connection_cursor()
    cursor.execute(query)
    dataframe = pd.DataFrame(data=cursor.fetchall())
    show_core_stocks(dataframe=dataframe)