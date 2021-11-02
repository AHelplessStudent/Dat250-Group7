# Overview

## Starting SQL-server

Download the sql-server [here](https://www.mysql.com).

Follow the commands (after config)

Start server

```bash
mysqld
```

Communicate w/server

```bash
mysql -u root
```

# Rest-API Overview

Polls can be accessed through `/polls`. Votes can be accessed through `/polls/{pid}/votes` or `/votes`. We originally
thought of not doing a `/votes`-API, but later found out it might make sense anyway.

## GET

```
/polls
```

```
/polls/{pid}
```

```
/polls/{pid}/votes
```

```
/votes
```

```
/votes/{vid}
```

```
/accounts
```

```
/accounts/{id}
```

## DELETE

```
/polls/{pid}
```

```
/votes/{vid}
```

```
/accounts/{id}
```

## PUT

```
/polls/{pid}
```

```
/votes/{vid}
```

```
/accounts/{id}
```

## POST

```
/polls
```

```
/polls/{pid}/votes
```

```
/votes
```

```
/accounts
```

