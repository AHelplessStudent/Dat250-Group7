# Set up DTO checklist:

- Create DTO classes
	- figure out which values to use in the DTOs (Almost complete, check after implementing it)

- Create a Model Mapper with MapService class
	- AutoWire repositories to MapService  (Check)
	- create methods for converting to DTOs (Check)
	- create Get methods for all polls/user / poll/user by id  (Check)

- Change Controllers to use DTOs instead of Entities
	- AutoWire MapService in Controllers
	- Keep Repositories for requests where DTO is not needed.

- Add ResponseEntitiy for better HTTPStatus control

- No need to change Entity classes. 

