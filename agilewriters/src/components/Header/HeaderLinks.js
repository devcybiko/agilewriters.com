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
    let loggedin = Cookies.get("loggedin") === "true";
    return (
            <List className={classes.list}>
            {!loggedin ? (<ListItem className={classes.listItem}><a className={classes.navLink} href="https://agilewriter-workshop.auth.us-east-1.amazoncognito.com/login?client_id=1vvqmdehb7iv0fdpqf3ne0r4qs&response_type=code&scope=email+openid&redirect_uri=http://localhost:8080/auth/login&state=STATE" >Login</a></ListItem>) :
                        (<ListItem className={classes.listItem}><a className={classes.navLink} href="http://localhost:8080/auth/logout">Logout</a></ListItem>)
            }
            <ListItem className={classes.listItem}><a className={classes.navLink} href="http://localhost:8080/auth/validate">Validate</a></ListItem>
        </List>
    );
}
