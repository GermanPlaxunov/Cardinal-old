from database.QueryProvider import get_rsi_query
from database.connection.ConnectionFactory import get_connection_cursor
from database.plot.Plotter import show_rsi
import pandas as pd


def visualize():
    query = get_rsi_query()
    cursor = get_connection_cursor()
    cursor.execute(query)
    dataframe = pd.DataFrame(data=cursor.fetchall())
    show_rsi(dataframe=dataframe)