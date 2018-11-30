var firstName = 'Michael';
var lastName = 'Jackson';
var year = 1958;
class foo {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    toString() {
        return '(' + this.x + ', ' + this.y + ')';
    }
}

export { firstName, lastName, year };

export function multiply(x, y) {
    return x * y;
}

export default foo