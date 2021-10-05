# Rest-API Overview

Syntax:

* `/polls/{pid}` - user should enter pid
* `/polls/(a, b, c)` - user selects between the options (fields) a, b and c
    * Short-hand for `/polls/a` OR `/polls/b` OR `/polls/c`

## GET

```
/polls/
```

```
/polls/{pid}
```

```
/polls/{pid}/votes
```

```
/polls/{pid}/votes/{vid}
```

```
/polls/{pid}/(title, deadline, public)
```

```
/votes
```

```
/votes/{vid}
```

```
/votes/{vid}/(num_yes, num_no)
```