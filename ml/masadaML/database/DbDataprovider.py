import pandas as pd

from masadaML.database.PostgresqlConnector import get_connection


def prepare_string_param(param):
    return '\'' + str(param) + '\''


def get_price_history_from_db(from_date, to_date, symbol):
    connection = get_connection()
    cursor = connection.cursor()
    query = f'select t.* from masada.core_stock_entity t ' \
            f'where t.symbol = {prepare_string_param(symbol)} ' \
            f'and t.date between {prepare_string_param(from_date)} ' \
            f'and {prepare_string_param(to_date)}'
    cursor.execute(query)
    result = cursor.fetchall()
    return result


def get_known_period_dates(symbol):
    connection = get_connection()
    cursor = connection.cursor()
    query = f'select min(t.date) as date_from, max(t.date) as date_to ' \
            f'from masada.core_stock_entity t ' \
            f'where t.symbol = {prepare_string_param(symbol)}'
    cursor.execute(query)
    result = cursor.fetchall()
    return (result[0][0], result[0][1])


def get_price_history(symbol):
    from_date, to_date = get_known_period_dates(symbol)
    dataset = get_price_history_from_db(from_date=from_date,
                                        to_date=to_date,
                                        symbol=symbol)
    dataframe = pd.DataFrame(data=dataset)
    dataframe = dataframe.rename(columns={0: 'id',
                                          1: 'close',
                                          2: 'date',
                                          3: 'high',
                                          4: 'low',
                                          5: 'open',
                                          6: 'symbol',
                                          7: 'volume_curr',
                                          8: 'volume_usd'})
    return dataframe
