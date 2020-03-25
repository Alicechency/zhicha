-- declare delimiter
delimiter // 

-- create procedure batch_insert_relationship_3
drop procedure batch_insert_relationship_3;
create procedure batch_insert_relationship_3()
begin
  declare batch_size int default 10000;
  declare sum_count int default 0;
  declare startRow int default 0;
  declare endRow int default 0;
  set endRow = batch_size;
  select count(1) from dw_crimedata into sum_count;
  while sum_count > 0 do
	insert into dim_crime_relationship
	select distinct a.`警情序号`as 'crime_id',3 as casetype,a.`事主` as casevalues, 1 as distince,null,0
	from dw_crimedata a limit startRow,endRow;
    set sum_count = sum_count - batch_size;
    set startRow = endRow;
    if sum_count < batch_size then
      set endRow = sum_count;
    end if;
  end while;
end;//

-- call all of procedures
call batch_insert_relationship_3;