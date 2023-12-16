--Delete all stocks which are later that cache depth
delete from libra.core_stock cs
       where cs.date > to_timestamp('2017-01-01 02:00:00', 'YYYY-MM-DD HH24:MI:ss');

-- Last provided stock should be equal to last core stock
update libra.last_provided_stock last set stock_id = 1051081,
                                          stock_date = (
 select t.date from libra.market_stock t where t.id = 1051081)
where id is not null;


-- clear process data
delete from libra.price_diff_signal where id is not null;
delete from libra.position where id is not null;

--indexes
delete from libra.apo where id is not null;
delete from libra.bbands where id is not null;
delete from libra.ema where id is not null;
delete from libra.rsi where id is not null;
delete from libra.sma where id is not null;
delete from libra.std_derivs where id is not null;

-- reset neural networks
delete from libra.neural_network where id is not null;

-- reset account
update libra.account set balance = 5000,
                         open_position_cnt = 0,
                         is_active = true
                     where account_id = '9b6afcd3-8126-4ca9-a871-e66f409e1d68';
commit;