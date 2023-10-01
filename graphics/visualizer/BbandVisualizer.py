from database.QueryProvider import get_bband_query
from database.connection.ConnectionFactory import get_connection_cursor
from database.plot.Plotter import show_bband
import pandas as pd


def visualize():
    query = get_bband_query()
    cursor = get_connection_cursor()
    cursor.execute(query)
    dataframe = pd.DataFrame(data=cursor.fetchall())
    show_bband(dataframe=dataframe)