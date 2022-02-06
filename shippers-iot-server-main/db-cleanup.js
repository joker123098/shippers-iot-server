db.asset.updateMany({},{
  $unset: {
    lastPosition: 1,
    currentPosition: 1
  },
  $set: {
    auditAction: "Create",
    positions: [],
    currentLocations: [],
    numberOfPositionUpdates: 0
  }
});

db.enterprise.updateMany({}, {
  $set: {
    "locations.$[].numberOfAssets": 0
  },
  $unset: {
    "locations.$[].eventUpdate": 1
  }
});

db.event.remove({});
db.location.remove({});
