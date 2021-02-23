/*eslint-disable*/
import React from "react";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";
// react components for routing our app without refresh
import { Link } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Tooltip from "@material-ui/core/Tooltip";

// @material-ui/icons
import { Apps, CloudDownload } from "@material-ui/icons";

import styles from "assets/jss/material-kit-react/components/headerLinksStyle.js";

const useStyles = makeStyles(styles);

export default function HeaderLinks(props) {
  const classes = useStyles();
  return (
    <List className={classes.list}>
      <ListItem className={classes.listItem}><a className={classes.navLink} href="https://agilewriter-workshop.auth.us-east-1.amazoncognito.com/login?response_type=code&client_id=1p1lco5v2jiko3p34dqon9i3m5&redirect_uri=http://localhost:8080/index.html&scope=email&state=STATE" >Login</a></ListItem>
      <ListItem className={classes.listItem}><a className={classes.navLink} href="https://agilewriter-workshop.auth.us-east-1.amazoncognito.com/login?response_type=code&client_id=1p1lco5v2jiko3p34dqon9i3m5&redirect_uri=http://localhost:8080/index.html&scope=email&state=STATE">Register</a></ListItem>
    </List>
  );
}
