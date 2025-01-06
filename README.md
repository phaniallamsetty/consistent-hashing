# Consistent Hashing

This project is a simple implementation of the **Consistent Hashing** technique. The consistent hashing algorithm is used in scenarios where horizontal scaling is needed and a normal hashing method does not distribute the load evenly across multiple nodes in the address space. For details on Consistent Hashing, visit this [link](https://en.wikipedia.org/wiki/Consistent_hashing)

## Implementation
The address space is represented by the class HashingRing. The class supports the following methods:
- **addNode()** : Creates a server on the ring and also creates virtual nodes. This method also redistributes any existing pieces of data which get affected by the addition of this node.
- **removeNode()** : Deletes a server and its virtual nodes. If the node being requested to delete is a virtual node, only the virtual node is deleted. The method also redistributes any existing pieces of data which might be affected due to the removal of the current server or the virtual node.
- **get()** : Returns the data present in a given node. The given node is passed as an input parameter.
- **saveData()** : Saves the data in the appropriate node based on the hashing algorithm and the virtual node mapping logic.
- **displayRingState()** : Prints the current node, not type, and the data stored in that node in a readable format.